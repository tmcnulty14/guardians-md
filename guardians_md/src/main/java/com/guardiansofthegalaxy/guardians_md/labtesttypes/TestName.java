package com.guardiansofthegalaxy.guardians_md.labtesttypes;

/**
 * Created by Christina on 4/24/2015.
 */
public enum TestName {

    /**
     * Enums for TestName
     */
    RED {
        @Override
        public String toString() {
            return "RED BLOOD CELL TEST";
        }
    },
    WHITE {
        @Override
        public String toString() {
            return "WHITE BLOOD CELL TEST";
        }
    },
    LIVER {
        @Override
        public String toString() {
            return "LIVER FUNCTION TEST";
        }
    },
    RENAL{
        @Override
    public String toString(){
            return "RENAL FUNCTION TEST";
        }
    },
    ELECTROL {
        @Override
        public String toString() {
            return "ELECTROL TEST";
        }
    },
    XRAY {
        @Override
        public String toString() {
            return "X-RAY";
        }
    },
    CT {
        @Override
        public String toString() {
            return "COMPUTED TOMOGRAPHY";
        }
    },
    MRI{
        @Override
        public String toString(){
            return "MAGNETIC RESONANCE IMAGE";
        }
    },
    URINARY {
        @Override
        public String toString() {
            return "URINARY TEST";
        }
    },
    STOOL{
        @Override
        public String toString(){
            return "STOOL TEST";
        }
    }
}
