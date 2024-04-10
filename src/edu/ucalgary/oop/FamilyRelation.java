package edu.ucalgary.oop;

public class FamilyRelation {

    private DisasterVictim personOne;
    private RelationshipType relationshipTo;
    private DisasterVictim personTwo;

    public FamilyRelation(DisasterVictim personOne, RelationshipType relationshipTo, DisasterVictim personTwo) {
        this.personOne = personOne;
        this.relationshipTo = relationshipTo;
        this.personTwo = personTwo;
    }

    public DisasterVictim getPersonOne() {
        return personOne;
    }

    public RelationshipType getRelationshipTo() {
        return relationshipTo;
    }

    public DisasterVictim getPersonTwo() {
        return personTwo;
    }

    public void setPersonOne(DisasterVictim personOne) {
        this.personOne = personOne;
    }

    public void setRelationshipTo(RelationshipType relationshipTo) {
        this.relationshipTo = relationshipTo;
    }

    public void setPersonTwo(DisasterVictim personTwo) {
        this.personTwo = personTwo;
    }

    public boolean compareRelations(FamilyRelation fr) {
        if ((this.relationshipTo != fr.relationshipTo)
                && ((this.relationshipTo == RelationshipType.CTP && fr.relationshipTo == RelationshipType.PTC) == false)
                && ((this.relationshipTo == RelationshipType.PTC
                        && fr.relationshipTo == RelationshipType.CTP) == false)) {
            return false;
        } else if (this.personOne == fr.personOne && this.personTwo == fr.personTwo) {
            return true;
        } else if (this.personOne == fr.personTwo && this.personTwo == fr.personOne) {
            return true;
        } else {
            return false;
        }
    }
}
