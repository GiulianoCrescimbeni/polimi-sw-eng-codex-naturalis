package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.Enumerations.AnglePos;
public class Angle {

    private Resource resource;
    private AnglePos position;
    private boolean hidden;
    private Angle attached;
    private Card card;

    public Angle(Resource resource, AnglePos position, boolean hidden, Angle attached, Card card) {
        this.resource = resource;
        this.position = position;
        this.hidden = hidden;
        this.attached = attached;
        this.card = card;
    }

    public Resource getResource() {
        return this.resource;
    }

    public AnglePos getPosition() {
        return position;
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean isAttached() {
        if (this.attached == null) {
            return false;
        } else return true;
    }

    public Angle getAttached() {
        return attached;
    }

    public Card getCard() {
        return this.card;
    }

    public void setHidden() {
        this.hidden = true;
    }

    public void setAttached(Angle angle) {
        this.attached = angle;
    }
}
