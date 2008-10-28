package org.sonatype.plugins.portallocator;

import java.io.IOException;
import java.net.ServerSocket;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

/**
 * Allocate ports to be used during build process
 *
 * @goal allocate-ports
 * @author velo
 */
public class PortAllocatorMojo
    extends AbstractMojo
{

    /**
     * Define which ports should be allocated by the plugin
     *
     * @parameter
     * @required
     */
    private Port[] ports;

    /**
     * @parameter expression="${project}"
     * @required
     */
    private MavenProject project;

    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
        for ( Port port : ports )
        {
            String name = port.getName();
            if ( name == null )
            {
                getLog().warn( "Port name not defined.  Skipping." );
                continue;
            }

            String type = port.getType();
            int portNumber;
            if ( Port.DEFAULT.equals( type ) )
            {
                portNumber = handleDefaultPort( port );
            }
            else
            {
                throw new MojoFailureException( "Unssuported port type '" + type + "' for '" + port.getName() + "'" );
            }

            getLog().info( "Assigning port '" + portNumber + "' to property '" + name + "'" );
            project.getProperties().put( name, String.valueOf( portNumber ) );
        }
    }

    private int handleDefaultPort( Port port )
        throws MojoFailureException, MojoExecutionException
    {
        try
        {
            // try to allocate the desired port
            return allocate( port.getPortNumber() );
        }
        catch ( PortUnavailableException e )
        {
            // fail if the desired port should be allocated
            if ( port.getFailIfOccupied() )
            {
                throw e;
            }
            else
            {
                // or try a random port if this is not a problem
                return allocate( 0 );
            }
        }
    }

    private int allocate( int portNumber )
        throws PortUnavailableException, MojoFailureException, MojoExecutionException
    {
        ServerSocket server;
        try
        {
            server = new ServerSocket( portNumber );
        }
        catch ( IOException e )
        {
            throw new PortUnavailableException( e.getLocalizedMessage(), e );
        }

        portNumber = server.getLocalPort();
        try
        {
            server.close();
        }
        catch ( IOException e )
        {
            throw new MojoExecutionException( "Unable to release port " + portNumber, e );
        }
        return portNumber;
    }

}
