package org.sonatype.plugins.portallocator;

public class Port
{

    public static final String DEFAULT = "default";

    /**
     * When true will break the build if the preferred port number is not available. If false will allocate another
     * port.
     */
    private boolean failIfOccupied;

    /**
     * Port name used to add to properties. Required.
     */
    private String name;

    /**
     * A preferred port
     */
    private int portNumber;

    /**
     * The port type. At present time the only accepted value is 'default'
     */
    private String type = DEFAULT;

    public Port()
    {
        super();
    }

    public Port( String name )
    {
        super();
        this.name = name;
    }

    public boolean getFailIfOccupied()
    {
        return failIfOccupied;
    }

    public String getName()
    {
        return name;
    }

    public int getPortNumber()
    {
        return portNumber;
    }

    public String getType()
    {
        return type;
    }

    public void setFailIfOccupied( boolean failIfOccupied )
    {
        this.failIfOccupied = failIfOccupied;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public void setPortNumber( int portNumber )
    {
        this.portNumber = portNumber;
    }

    public void setType( String type )
    {
        this.type = type;
    }
}
