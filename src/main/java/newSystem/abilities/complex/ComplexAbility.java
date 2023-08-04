package newSystem.abilities.complex;

import newSystem.abilities.Ability;
import newSystem.abilities.IAbility;

import java.util.ArrayList;
import java.util.Arrays;

//
// Habilidad altamente configurable
//
public class ComplexAbility extends Ability
{
    private final IComplexCheck[] complexChecks;

    public ComplexAbility(String name, IAbility abilityLogic, IComplexCheck[] complexChecks)
    {
        super(name, abilityLogic);
        this.complexChecks = complexChecks;
    }

    @Override
    public void invoke()
    {

        for (IComplexCheck complexCheck : complexChecks)
        {
            if (!complexCheck.tryPreCheck())
            {
                // Invocar los fail
                for (IComplexCheck complexCheckFailed : complexChecks)
                    complexCheckFailed.onPreCheckFail();

                return;
            }
        }

        for (IComplexCheck complexCheck : complexChecks)
            complexCheck.onPreCheckSuccess();

        this.abilityLogic.invoke();

        for (IComplexCheck complexCheck : complexChecks)
        {
            if (!complexCheck.tryPostCheck())
            {
                // Invocar los fail
                for (IComplexCheck complexCheckFailed : complexChecks)
                    complexCheckFailed.onPostCheckFail();

                return;
            }
        }

        for (IComplexCheck complexCheck : complexChecks)
            complexCheck.onPostCheckSuccess();
    }
}
