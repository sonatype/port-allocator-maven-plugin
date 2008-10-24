package org.sonatype.plugins.portallocator;

public class Port
{

    public static final String DEFAULT = "default";

    private boolean failIfOccupied;

    private String name;

    private int portNumber;

    private String type = DEFAULT;

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

    public boolean getFailIfOccupied()
    {
        return failIfOccupied;
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
