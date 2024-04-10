package edu.ucalgary.oop;

public enum DietaryRestriction {
    AVML {
        @Override
        public String getFullName() {
            return "Asian vegetarian meal";
        }
    },
    DBML {
        @Override
        public String getFullName() {
            return "Diabetic meal";
        }
    },
    GFML {
        @Override
        public String getFullName() {
            return "Gluten intolerant meal";
        }
    },
    KSML {
        @Override
        public String getFullName() {
            return "Kosher meal";
        }
    },
    LSML {
        @Override
        public String getFullName() {
            return "Low salt meal";
        }
    },
    MOML {
        @Override
        public String getFullName() {
            return "Muslim meal";
        }
    },
    PFML {
        @Override
        public String getFullName() {
            return "Peanut-free meal";
        }
    },
    VGML {
        @Override
        public String getFullName() {
            return "Vegan meal";
        }
    },
    VJML {
        @Override
        public String getFullName() {
            return "Vegetarian Jain meal";
        }
    };

    public abstract String getFullName();
}
