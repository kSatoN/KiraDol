package ksato.kiradol.models;

enum ETypeOfKiraidaaa
{
	Static('s'),
	Moving('m'),
	GoAfter('g');
	
	private final char id;
	
	private ETypeOfKiraidaaa(final char c)
	{
		id = c;
		return;
	}
	
	char getId( )
	{
		return id;
	}
	
}
