package newSystem.fruits;

import newSystem.OPUser;
import newSystem.abilities.AbilitySet;

import java.util.ArrayList;

public abstract class DevilFruit
{
    protected String name;
    protected ArrayList<AbilitySet> abilitySets = new ArrayList<>();
    protected OPUser user;

    public DevilFruit(String name)
    {
        this.name = name;
    }

    // Permite robar frutas temporalmente
    public void setUser(OPUser user)
    {
        this.user = user;
    }

    public String getName()
    {
        return this.name;
    }

    public void invokeAbility(int abilitySetID, int abilityID)
    {
        if (abilitySetID < 0 || abilitySetID >= this.abilitySets.size())
            return;

        this.abilitySets.get(abilitySetID).invokeAbility(abilityID);
    }

    public ArrayList<AbilitySet> getAbilitySets()
    {
        return this.abilitySets;
    }
}
