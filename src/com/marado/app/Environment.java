package com.marado.app;

import com.marado.graphicInterface.UI;

public class Environment {
    //SOLAR SYSTEM GRAVITIES
    //STARS
    private final static double SUN_GRAVITY = 274;
    //PLANETS
    private final static double MERCURY_GRAVITY = 3.7;
    private final static double VENUS_GRAVITY = 8.87;
    private final static double EARTH_GRAVITY = 9.80665;
    private final static double MARS_GRAVITY = 3.72076;
    private final static double JUPITER_GRAVITY = 24.79;
    private final static double SATURN_GRAVITY = 10.44;
    private final static double URANUS_GRAVITY = 8.69;
    private final static double NEPTUNE_GRAVITY = 11.15;
    //SATELLITIES
    private final static double MOON_GRAVITY = 1.62;//EARTH MOON
    private final static double GANYMEDE_GRAVITY = 1.428;//JUPITER MOON
    private final static double TITAN_GRAVITY = 1.352;//SATURN MOON
    private final static double CALLISTO_GRAVITY = 1.235;//JUPITER MOON
    private final static double IO_GRAVITY = 1.796;//JUPITER MOON
    private final static double EUROPA_GRAVITY = 1.314;//JUPITER MOON
    private final static double TRITON_GRAVITY = 0.799;//NEPTUNE MOON
    private final static double TITANIA_GRAVITY = 0.379;//URANUS MOON
    private final static double RHEA_GRAVITY = 0.264;//SATURN MOON
    private final static double OBERON_GRAVITY = 0.346;//URANUS MOON
    private final static double IAPETUS_GRAVITY = 0.223;//SATURN MOON
    private final static double CHARON_GRAVITY = 0.288;//PLUTO MOON
    private final static double UMBRIEL_GRAVITY = 0.2;//URANUS MOON
    private final static double ARIEL_GRAVITY = 0.269;//URANUS MOON
    private final static double DIONE_GRAVITY = 0.232;//SATURN MOON
    private final static double TETHYS_GRAVITY = 0.146;//SATURN MOON
    private final static double ENCELADUS_GRAVITY = 0.113;//SATURN MOON
    private final static double MIRANDA_GRAVITY = 0.079;//URANUS MOON
    private final static double PROTEUS_GRAVITY = 0.07;//NEPTUNE MOON
    private final static double MIMAS_GRAVITY = 0.064;//SATURN MOON
    private final static double HYPERION_GRAVITY = Math.random()*(0.021-0.017)+0.017;//SATURN MOON
    private final static double PHOEBE_GRAVITY = Math.random()*(0.05-0.038)+0.038;//SATURN MOON
    private final static double JANUS_GRAVITY = Math.random()*(0.017-0.011)+0.011;//SATURN MOON
    private final static double EPIMETHEUS_GRAVITY = Math.random()*(0.011-0.0064)+0.0064;//SATURN MOON
    private final static double PROMETHEUS_GRAVITY = Math.random()*(0.0058-0.0013)+0.0013;//SATURN MOON
    private final static double PANDORA_GRAVITY = Math.random()*(0.006-0.0026)+0.0026;//SATURN MOON
    private final static double PHOBOS_GRAVITY = 0.003;//MARS MOON
    private final static double DEIMOS_GRAVITY = 0.003;//MARS MOON
    //DWARF PLANETS
    private final static double PLUTO_GRAVITY = 0.62;
    private final static double CERES_GRAVITY = 0.28;
    
    public static double gravity = EARTH_GRAVITY;
    
    public enum celestialObjects{
        SUN        ("sun",        SUN_GRAVITY),
        MERCURY    ("mercury",    MERCURY_GRAVITY),
        VENUS      ("venus",      VENUS_GRAVITY),
        EARTH      ("earth",      EARTH_GRAVITY),
        MARS       ("mars",       MARS_GRAVITY),
        JUPITER    ("jupiter",    JUPITER_GRAVITY),
        SATURN     ("saturn",     SATURN_GRAVITY),
        URANUS     ("uranus",     URANUS_GRAVITY),
        NEPTUNE    ("neptune",    NEPTUNE_GRAVITY),
        MOON       ("moon",       MOON_GRAVITY),
        GANYMEDE   ("ganymede",   GANYMEDE_GRAVITY),
        TITAN      ("titan",      TITAN_GRAVITY),
        CALLISTO   ("callisto",   CALLISTO_GRAVITY),
        IO         ("io",         IO_GRAVITY),
        EUROPA     ("europa",     EUROPA_GRAVITY),
        TRITON     ("triton",     TRITON_GRAVITY),
        TITANIA    ("titania",    TITANIA_GRAVITY),
        RHEA       ("rhea",       RHEA_GRAVITY),
        OBERON     ("oberon",     OBERON_GRAVITY),
        IAPETUS    ("iapetus",    IAPETUS_GRAVITY),
        CHARON     ("charon",     CHARON_GRAVITY),
        UMBRIEL    ("umbriel",    UMBRIEL_GRAVITY),
        ARIEL      ("ariel",      ARIEL_GRAVITY),
        DIONE      ("dione",      DIONE_GRAVITY),
        TETHYS     ("tethys",     TETHYS_GRAVITY),
        ENCELADUS  ("enceladus",  ENCELADUS_GRAVITY),
        MIRANDA    ("miranda",    MIRANDA_GRAVITY),
        PROTEUS    ("proteus",    PROTEUS_GRAVITY),
        MIMAS      ("mimas",      MIMAS_GRAVITY),
        HYPERION   ("hyperion",   HYPERION_GRAVITY),
        PHOEBE     ("phoebe",     PHOEBE_GRAVITY),
        JANUS      ("janus",      JANUS_GRAVITY),
        EPIMETHEUS ("epimetheus", EPIMETHEUS_GRAVITY),
        PROMETHEUS ("prometheus", PROMETHEUS_GRAVITY),
        PANDORA    ("pandora",    PANDORA_GRAVITY),
        PHOBOS     ("phobos",     PHOBOS_GRAVITY),
        DEIMOS     ("deimos",     DEIMOS_GRAVITY),
        PLUTO      ("pluto",      PLUTO_GRAVITY),
        CERES      ("ceres",      CERES_GRAVITY);
        
        private final String NAME;
        private final double GRAVITY;

        private celestialObjects(String NAME, double GRAVITY) {
            this.NAME = NAME;
            this.GRAVITY = GRAVITY;
        }
        
        public static boolean isCelestialObject(String cmdArg){
            for(celestialObjects co : values())
                if(co.NAME.equals(cmdArg)){
                    return true;
                }
            return false;
        }
        
        public static void changeGravity(String cmdArg){
            if(UI.isNumeric(cmdArg))
                gravity = Double.parseDouble(cmdArg);
            for(celestialObjects co : values())
                if(co.NAME.equals(cmdArg)){
                    gravity = co.GRAVITY;
                    break;
                }
        }
    }
    
    public static String getGravity(){
        return (String.format("Gravity: %.3fm/(s^2)", gravity));
    }
    
}
