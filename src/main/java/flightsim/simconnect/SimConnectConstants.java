package flightsim.simconnect;

import flightsim.simconnect.recv.RecvSimObjectDataByType;

/**
 * Contains all SimConnect constants not a part of an enumeration
 * 
 * @see SimObjectType
 * @see NotificationPriority
 * @see SimConnectDataType
 * @see SimConnectPeriod
 * @author lc0277
 *
 */
public interface SimConnectConstants {
	/** The default, data will be sent strictly according to the defined period. */
	public static final int DATA_REQUEST_FLAG_DEFAULT           = 0x00000000;  
	/** Data will only be sent to the client when one or more values have changed. If 
	 * this is the only flag set, then all the variables in a data definition will be returned 
	 * if just one of the values changes. */
	public static final int DATA_REQUEST_FLAG_CHANGED           = 0x00000001;      // send requested data when value(s) change
	/** Requested data will be sent in tagged format (datum ID/value pairs). Tagged 
	 * format requires that a datum reference ID is returned along with the data value, 
	 * in order that the client code is able to identify the variable. This flag is 
	 * usually set in conjunction with the previous flag, but it can be used on its own to 
	 * return all the values in a data definition in datum ID/value pairs */
	public static final int DATA_REQUEST_FLAG_TAGGED            = 0x00000002;      // send requested data in tagged format
	
	/** The data to be set is not in tagged format */
	public static final int DATA_SET_FLAG_DEFAULT               = 0x00000000;      // data is in tagged format
	/** The data to be set is being sent in tagged format. Refer to {@link SimConnect#requestDataOnSimObject(int, int, int, SimConnectPeriod, int, int, int, int)} for more details on the tagged format. */
	public static final int DATA_SET_FLAG_TAGGED                = 0x00000001;      // data is in tagged format
	
	/** The default, data will be sent strictly according to the defined period. 
	 * @since 0.5 
	 */
	public static final int CLIENT_DATA_REQUEST_FLAG_DEFAULT           = 0x00000000;  
	/** Data will only be sent to the client when one or more values have changed. If 
	 * this is the only flag set, then all the variables in a data definition will be returned 
	 * if just one of the values changes.
	 * @since 0.5
	 */
	public static final int CLIENT_DATA_REQUEST_FLAG_CHANGED           = 0x00000001;      // send requested data when value(s) change
	/** Requested data will be sent in tagged format (datum ID/value pairs). Tagged 
	 * format requires that a datum reference ID is returned along with the data value, 
	 * in order that the client code is able to identify the variable. This flag is 
	 * usually set in conjunction with the previous flag, but it can be used on its own to 
	 * return all the values in a data definition in datum ID/value pairs. 
	 * @since 0.5
	 */
	public static final int CLIENT_DATA_REQUEST_FLAG_TAGGED            = 0x00000002;      // send requested data in tagged format

	
	/** The data to be set is not in tagged format 
	 * @since 0.5
	 */
	public static final int CLIENT_DATA_SET_FLAG_DEFAULT               = 0x00000000;      // data is in tagged format
	/** The data to be set is being sent in tagged format. Refer to {@link SimConnect#requestDataOnSimObject(int, int, int, SimConnectPeriod, int, int, int, int)} for more details on the tagged format. */
	public static final int CLIENT_DATA_SET_FLAG_TAGGED                = 0x00000001;      // data is in tagged format
	

	
	/** Specify the user aircraft in {@link RecvSimObjectDataByType} and {@link SimConnect#requestDataOnSimObject(int, int, int, SimConnectPeriod)} */
	public static final int OBJECT_ID_USER   	= 0;  

	public static final int UNUSED 				= 0xFFFFFFFF;
	
	/** Do nothing. */
	public static final int EVENT_FLAG_DEFAULT                  = 0x00000000;      
	/** The flag will effectively reset the repeat timer to simulate slow repeat. Use this flag to 
	 * reset the repeat timer that works with various keyboard events and mouse clicks. */
	public static final int EVENT_FLAG_FAST_REPEAT_TIMER        = 0x00000001;      // set event repeat timer to simulate fast repeat
	/** The flag will effectively reset the repeat timer to simulate slow repeat. 
	 Use this flag to reset the repeat timer that works with various keyboard events and mouse clicks. */
	public static final int EVENT_FLAG_SLOW_REPEAT_TIMER        = 0x00000002;      // set event repeat timer to simulate slow repeat
	/** Indicates to the SimConnect server to treat the GroupID as a priority value. 
	 If this parameter is set to {@link NotificationPriority#HIGHEST} then all 
	 client notification groups that have subscribed to the event will receive 
	 the notification (unless one of them masks it). The event will be transmitted 
	 to clients starting at the given priority, though this can result in the 
	 client that transmitted the event, receiving it again. */
	public static final int EVENT_FLAG_GROUPID_IS_PRIORITY      = 0x00000010;      // interpret GroupID parameter as priority value

	/** current (and max) protocol version supported by this implementation of jsimconnect */
	public static final int PROTO_VERSION			= 4;
	
	public static final int RECEIVE_SIZE 			= 65536;	
	

	/** indicates that the value for the camera should be taken unmodified from the reference point. */
	public static final float CAMERA_IGNORE_FIELD	= Float.MAX_VALUE;  //Used to tell the Camera API to NOT modify the value in this part of the argument.


//	Weather observations Metar strings
	public static final int MAX_METAR_LENGTH = 2000;

//	 Maximum thermal size is 100 km.
	public static final float MAX_THERMAL_SIZE = 100000;
	public static final float MAX_THERMAL_RATE = 1000;

//	 SIMCONNECT_DATA_INITPOSITION.Airspeed
	/** The aircraft's design cruising speed. */
	public static final int INITPOSITION_AIRSPEED_CRUISE = -1;       // aircraft's cruise airspeed
	/** Maintain the current airspeed.  */
	public static final int INITPOSITION_AIRSPEED_KEEP = -2;         // keep current airspeed

	/** a MS Windows constant */
	public static final int MAX_PATH = 260;
	
	/** Specifies requested speed is valid. */
	public static final int WAYPOINT_SPEED_REQUESTED = 0x04;
	/** Specifies requested throttle percentage is valid. */
	public static final int WAYPOINT_THROTTLE_REQUESTED = 0x08;
	/** Specifies that the vertical should be calculated to reach the required speed when crossing the waypoint. */
	public static final int WAYPOINT_COMPUTE_VERTICAL_SPEED = 0x10;
	/** Specifies the altitude specified is AGL (above ground level). */
	public static final int WAYPOINT_ALTITUDE_IS_AGL = 0x20;
	/** Specifies the waypoint should be on the ground. Make sure this flag is set if the aircraft is to taxi to this point. */
	public static final int WAYPOINT_ON_GROUND = 0x100000;
	/** Specifies that the aircraft should back up to this waypoint. This is only valid on the first waypoint. */
	public static final int WAYPOINT_REVERSE = 0x200000;
	/** Specifies that the next waypoint is the first waypoint. This is only valid on the last waypoint. */
	public static final int WAYPOINT_WRAP_TO_FIRST = 0x400000;
	
	/** When subscribed to event <code>MissionCompleted</code> */
	public static final int MISSION_FAILED = 0;
	/** When subscribed to event <code>MissionCompleted</code> */
	public static final int MISSION_CRASHED = 1;
	/** When subscribed to event <code>MissionCompleted</code> */
	public static final int MISSION_SUCCEEDED = 2;
	
	/** When subscribed to event <code>View</code> */
	public static final int VIEW_SYSTEM_EVENT_DATA_COCKPIT_2D      = 0x00000001;      // 2D Panels in cockpit view
	/** When subscribed to event <code>View</code> */
	public static final int VIEW_SYSTEM_EVENT_DATA_COCKPIT_VIRTUAL = 0x00000002;      // Virtual (3D) panels in cockpit view
	/** When subscribed to event <code>View</code> */
	public static final int VIEW_SYSTEM_EVENT_DATA_ORTHOGONAL      = 0x00000004;      // Orthogonal (Map) view

	/** When subsribed to event <code>Sound</event> */
	public static final int SOUND_SYSTEM_EVENT_DATA_MASTER = 1;
	
	/** unknow group received */
	public static final int UNKNOWN_GROUP = 0xFFFFFFFF;
	
	/** automatically compute offset of the ClientData variable */
	public static final int CLIENTDATAOFFSET_AUTO = -1;
	
	/**   Specifies that the user has selected the menu item. */
	public static final int TEXT_RESULT_MENU_SELECT_1 = 0;
	/**   Specifies that the user has selected the menu item. */
	public static final int TEXT_RESULT_MENU_SELECT_2 = 1;
	/**   Specifies that the user has selected the menu item. */
	public static final int TEXT_RESULT_MENU_SELECT_3 = 2;
	/**   Specifies that the user has selected the menu item. */
	public static final int TEXT_RESULT_MENU_SELECT_4 = 3;
	/**   Specifies that the user has selected the menu item. */
	public static final int TEXT_RESULT_MENU_SELECT_5 = 4;
	/**   Specifies that the user has selected the menu item. */
	public static final int TEXT_RESULT_MENU_SELECT_6 = 5;
	/**   Specifies that the user has selected the menu item. */
	public static final int TEXT_RESULT_MENU_SELECT_7 = 6;
	/**   Specifies that the user has selected the menu item. */
	public static final int TEXT_RESULT_MENU_SELECT_8 = 7;
	/**   Specifies that the user has selected the menu item. */
    public static final int TEXT_RESULT_MENU_SELECT_9 = 8;
	/**   Specifies that the user has selected the menu item. */
    public static final int TEXT_RESULT_MENU_SELECT_10 = 9;  
    /**   Specifies that the menu or text identified by the EventID is now on display. */
    public static final int TEXT_RESULT_DISPLAYED = 0x00010000;
    /**   Specifies that the menu or text identified by the EventID is waiting in a queue. */
    public static final int TEXT_RESULT_QUEUED = 0x00010001;
    /**   Specifies that the menu or text identified by the EventID has been removed from the queue. */
    public static final int TEXT_RESULT_REMOVED = 0x00010002;
    /**    Specifies that the menu or text identified by the EventID has been replaced in the queue. */
    public static final int TEXT_RESULT_REPLACED = 0x00010003;
    /**   Specifies that the menu or text identified by the EventID has timed-out and is no longer on display. */
    public static final int TEXT_RESULT_TIMEOUT = 0x00010004;

    /** @see SimConnect#addToClientDataDefinition(int, int, int, float, int) */
    public static final int CLIENT_DATA_TYPE_INT8	=	-1;
    /** @see SimConnect#addToClientDataDefinition(int, int, int, float, int) */
    public static final int CLIENT_DATA_TYPE_INT16	=	-2;
    /** @see SimConnect#addToClientDataDefinition(int, int, int, float, int) */
    public static final int CLIENT_DATA_TYPE_INT32	=	-3;
    /** @see SimConnect#addToClientDataDefinition(int, int, int, float, int) */
    public static final int CLIENT_DATA_TYPE_INT64	=	-4;
    /** @see SimConnect#addToClientDataDefinition(int, int, int, float, int) */
    public static final int CLIENT_DATA_TYPE_FLOAT32	=	-5;
    /** @see SimConnect#addToClientDataDefinition(int, int, int, float, int) */
    public static final int CLIENT_DATA_TYPE_FLOAT64	=	-6;

}
