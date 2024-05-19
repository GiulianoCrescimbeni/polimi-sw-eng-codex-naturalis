package it.polimi.ingsw.model.GameComponents;

import com.google.gson.annotations.SerializedName;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.Enumerations.AnglePos;

import java.io.Serializable;

/**
 * Class for angles of a card
 */
public class Angle implements Serializable {

    private Resource resource;
    private boolean hidden;
    private Angle attached;
    private Card card;

    /**
     * Constructor
     * @param resource {@link Resource} that the angle contains
     * @param hidden parameter to know if the angle is hidden or not
     * @param attached the {@link Angle} that is attached to this one
     * @param card the {@link Card} that contains the angle
     */
    public Angle(Resource resource, boolean hidden, Angle attached, Card card) {
        this.resource = resource;
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

    /**
     * Set the card of the angle
     * @param card the angle of the card
     */
    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        String resource = "";
        String hidden = "";
        String attached = "";
        String card = "";

        if (this.resource == null) resource = "Resource: Null\n"; else resource = "Resource: " + this.resource.toString() + "\n";
        if (this.hidden) hidden = "Hidden: True\n"; else hidden = "Hidden: False\n";
        if (this.attached == null) attached = "Attached: Null\n"; else attached = "Attached: " + this.attached.toString() + "\n";
        if (this.card == null) card = "Card: Null\n"; else card = "Card: " + this.card.getCardID() + "\n";

        return resource + hidden + attached + card;

    }
}
