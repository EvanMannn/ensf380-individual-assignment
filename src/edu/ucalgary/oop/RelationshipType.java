package edu.ucalgary.oop;

public enum RelationshipType {
    SP {
        @Override
        public String getFullRelationshipType() {
            return "Spouce";
        }

        @Override
        public RelationshipType getOppoRelationshipType() {
            return RelationshipType.SP;
        }
    },
    PTC {
        @Override
        public String getFullRelationshipType() {
            return "Parent to Child";
        }

        @Override
        public RelationshipType getOppoRelationshipType() {
            return RelationshipType.CTP;
        }
    },
    CTP {
        @Override
        public String getFullRelationshipType() {
            return "Child to Parent";
        }

        @Override
        public RelationshipType getOppoRelationshipType() {
            return RelationshipType.PTC;
        }
    },
    SI {
        @Override
        public String getFullRelationshipType() {
            return "Sibling";
        }

        @Override
        public RelationshipType getOppoRelationshipType() {
            return RelationshipType.SI;
        }
    };

    public abstract RelationshipType getOppoRelationshipType();

    public abstract String getFullRelationshipType();
}
