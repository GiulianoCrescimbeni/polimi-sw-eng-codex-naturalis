package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.Enumerations.AnglePos;

/**
 * Class for angles of a card
 */
public class Angle {

    private Resource resource;
    private AnglePos position;
    private boolean hidden;
    private Angle attached;
    private Card card;

    /**
     * Constructor
     * @param resource {@link Resource} that the angle contains
     * @param position {@link AnglePos} of the angle
     * @param hidden parameter to know if the angle is hidden or not
     * @param attached the {@link Angle} that is attached to this one
     * @param card the {@link Card} that contains the angle
     */
    public Angle(Resource resource, AnglePos position, boolean hidden, Angle attached, Card card) {
        this.resource = resource;
        this.position = position;
        this.hidden = hidden;
        this.attached = attached;
        this.card = card;
    }

    /**
     * @return the resource of the angle
     */
    public Resource getResource() {
        return this.resource;
    }

    /**
     * @return the position of the angle
     */
    public AnglePos getPosition() {
        return position;
    }

    /**
     * @return the hidden parameter of the angle
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * @return true if the angle is attached, false if not
     */
    public boolean isAttached() {
        if (this.attached == null) {
            return false;
        } else return true;
    }

    /**
     * @return the angle attached to this one
     */
    public Angle getAttached() {
        return attached;
    }

    /**
     * @return the card that contains the angle
     */
    public Card getCard() {
        return this.card;
    }

    /**
     * set the card to hidden
     */
    public void setHidden() {
        this.hidden = true;
    }

    /**
     * Set the attached angle
     * @param angle the angle that is attached to this one
     */
    public void setAttached(Angle angle) {
        this.attached = angle;
    }
}
