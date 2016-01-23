/**
 *
 * @author Preston Peterson
 * @version 11/01/2015
 */

public class BingoChip 
{
	private char letter;
	private int number;

	public BingoChip(int number)
	{
		setNumber( number );		
		setLetter();		
	}

	private void setNumber(int number)
	{
		this.number = number;
	}

	private void setLetter()
	{
		int index = (this.number - 1) / BingoCard.MAX_VALUES_PER_LETTER;
		this.letter = BingoCard.BINGO_KEYS.charAt(index);
	}

	public int getNumber()
	{
		return this.number;
	}

	public char getLetter()
	{
		return this.letter;
	}

	public String toString()
	{
		return letter + " " + number;
	}
}