package org.sonatype.plugins.portallocator;

import org.apache.maven.plugin.MojoFailureException;

public class PortUnavailableException
    extends MojoFailureException
{

    private static final long serialVersionUID = -7410759462209491374L;

    public PortUnavailableException( String message, Exception cause )
    {
        super( message );
        initCause( cause );
    }

}
