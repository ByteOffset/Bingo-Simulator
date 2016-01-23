/**
 *
 * @author Preston Peterson
 * @version 11/01/2015
 */
 
import java.util.*;

public class BingoGame 
{
	private final int NUM_OF_CHIPS = 75;
	private int numOfPlayers;
	private ArrayList<BingoChip> bingoDrum;
	private BingoCard[] playersCards;


	public BingoGame(int numOfPlayers) 
	{
		setNumOfPlayers(numOfPlayers);
		createBingoDrum();
		createPlayersCards();
	}
	
	private void createBingoDrum()
	{
		this.bingoDrum = new ArrayList<>(this.numOfPlayers);
		BingoChip chip;
		for (int i = 1; i <= this.NUM_OF_CHIPS; i++) {
			chip = new BingoChip(i);
			bingoDrum.add(chip);
		}
	}
	
	private void createPlayersCards()
	{
		this.playersCards = new BingoCard[this.numOfPlayers];
		BingoCard card;
		for (int i = 0; i < this.playersCards.length; i++) {
			card = new BingoCard();
			System.out.println("---> Creating bingo card for player " + (i + 1));
			this.playersCards[i] = card;
			card.printCard();
		}
	}
	
	private void setNumOfPlayers(int numOfPlayers)
	{
		this.numOfPlayers = numOfPlayers;
	}

	public int getNumberOfChips()
	{
		return this.NUM_OF_CHIPS - bingoDrum.size();
	}

	public BingoChip pullChip()
	{
		Random random = new Random();
		int rand = random.nextInt(bingoDrum.size());
		BingoChip pulledChip = bingoDrum.remove(rand);
		System.out.println("Calling " + pulledChip.getLetter() + " " + pulledChip.getNumber());
		return pulledChip;
	}
	
	public void play()
	{
		boolean finished = false;
		String winners ="";
		while (!finished) {
			BingoChip chip = pullChip();
			for (int i = 0; i < playersCards.length; i++) {
				playersCards[i].checkNumber(chip);
				System.out.println("Player " + (i+1) + "'s card:");
				playersCards[i].printCard();
				System.out.println();
				if (playersCards[i].isWinner()) {
					finished = true;
					winners+= "!!! Player " + (i+1) + " says BINGO!!!\n";
				}
			}

		}
		System.out.println(winners);
		System.out.println(getNumberOfChips() + " chips were called.");

	}
}