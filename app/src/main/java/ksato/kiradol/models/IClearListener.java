package ksato.kiradol.models;

public interface IClearListener
{
	public void changeClear(EClear typeOfClear, EGameOver gameOver);
	public void changeClear(EClear typeOfClear, String follower);
	
}
