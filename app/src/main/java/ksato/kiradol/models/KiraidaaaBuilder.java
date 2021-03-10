package ksato.kiradol.models;

import android.support.annotation.NonNull;


public abstract class KiraidaaaBuilder extends Critic implements IGameCharacter, ICritic
{
	@NonNull
	static KiraidaaaBuilder newKiraidaaa(int wx, int wy, ETypeOfKiraidaaa type)
	{
		switch(type)
		{
			case Static:
				return new KiraidaaaStatic(wx, wy);
			case Moving:
				return new KiraidaaaMoving(wx, wy);
			case GoAfter:
				return new KiraidaaaGoAfter(wx, wy);
			default:
				return new KiraidaaaStatic(wx, wy);
		}
	}
	
}
