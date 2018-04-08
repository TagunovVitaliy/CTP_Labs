import java.util.HashMap;
/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;
    private HashMap<Location, Waypoint> openPoints;
    private HashMap<Location, Waypoint> closePoints;

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
        openPoints = new HashMap<Location, Waypoint>();
        closePoints = new HashMap<Location, Waypoint>();

    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
		Waypoint[] p=new Waypoint[]{};
		p=openPoints.values().toArray(p);
		Waypoint minPoint=p[0];
		double min=minPoint.getTotalCost();
        for(int i=1;i<p.length; i++){
            double cost = p[i].getTotalCost();
            if (cost<min) {
                minPoint=p[i];
                min=cost;
            }
        }

        return minPoint;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        Waypoint a=openPoints.get(newWP.getLocation());
        if (a==null){
            openPoints.put(newWP.getLocation(),newWP);
            return true;
        }
        if(newWP.getPreviousCost()<a.getPreviousCost()){
            openPoints.replace(newWP.getLocation(),newWP);
            return true;
        }
        return false;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        return openPoints.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
	
	public void closeWaypoint(Location loc)
    {
        Waypoint p = openPoints.remove(loc);        
        if(p!=null)
        {
            closePoints.put(loc, p);
        }
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return closePoints.containsKey(loc);
    }
}