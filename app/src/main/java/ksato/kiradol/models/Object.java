package ksato.kiradol.models;

abstract class Object extends GameCharacter implements IGameCharacter, IObject
{
	protected IIdol idol;
	
	@Override
	public void setIdol(IIdol oIdol)
	{
		idol = oIdol;
		return;
	}
	
}
