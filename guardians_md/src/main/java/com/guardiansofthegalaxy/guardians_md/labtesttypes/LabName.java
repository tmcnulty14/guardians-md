package com.guardiansofthegalaxy.guardians_md.labtesttypes;

/**
 * Created by Christina on 4/24/2015.
 */
public enum LabName {
    /**
     * Enum for Lab Types
     */
    HEMATOLOGIC {
        @Override
        public String toString() {
            return "HEMATOLOGIC";
        }
    },

    RADIOLOGIC {
        @Override
        public String toString() {
            return "RADIOLOGIC";
        }
    },

    ADDITIONAL {
        @Override
        public String toString() {
            return "ADDITIONAL";
        }
    }


}
