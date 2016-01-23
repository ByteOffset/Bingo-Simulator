/**
 *
 * @author Preston Peterson
 * @version 11/01/2015
 */
 
import java.util.*;
public class BingoCard 
{
	private TreeSet<Character> bingoChars; //use this to collect letters during the game. winner is declard when the size of bingoChars is 5 (meaning it contains all letters).
	private HashMap<Character, TreeSet<Integer>> card;
    public final static String BINGO_KEYS = "BINGO";
    public final static int MAX_VALUES_PER_LETTER = 15;
    public final static int NUMBERS_PER_LETTER = 5;
	//B: 1-15	14/15 = 0 = index of B in BINGO_KEYS string
	//I: 16-30
	//N: 31-45
	//G: 46-60	50/15 = 3 = index of G in BINGO_KEYS string
	//O: 61-75


	//utilize while loop to generate numbers per bingo row

	public BingoCard()
	{
		this.bingoChars = new TreeSet<>();
		Random random = new Random();
		TreeSet<Integer> tree;
		int rand;
		char currentLetter;
		this.card = new HashMap<>();
		for (int i = 0; i < BINGO_KEYS.length(); i++) {
			tree = new TreeSet<>();
			currentLetter = BINGO_KEYS.charAt(i);
			while (tree.size() < NUMBERS_PER_LETTER) {
				rand = (MAX_VALUES_PER_LETTER * i) + random.nextInt(MAX_VALUES_PER_LETTER)+1;
				tree.add(rand);
			}
			this.card.put(currentLetter, tree);
		}
	}

	public void checkNumber(BingoChip chip)
	{
		char chipLetter = chip.getLetter();
		int chipNumber = chip.getNumber();
		TreeSet<Integer> tree = this.card.get(chipLetter);
		boolean hasNumber = false;
		for (Integer number : tree) {
			if (number == chipNumber) {
				hasNumber = true;
			}
		}
		if (hasNumber) {
			if (bingoChars.add(chipLetter)) {
				tree.add(0);
				this.card.replace(chipLetter, tree);
			}
		}
	}
	
	public boolean isWinner()
	{
		boolean result = false;
		if (!bingoChars.isEmpty() && this.bingoChars.size() == BINGO_KEYS.length())
			result = true;
		return result;
	}
	
	public void printCard()
	{
		for (int i = 0; i < this.card.size(); i++) {
			System.out.print(BINGO_KEYS.charAt(i) + " ");
			TreeSet<Integer> tree = this.card.get(BINGO_KEYS.charAt(i));
			for (Integer element : tree) {
				System.out.printf("%3d", element);
			}
			System.out.println();
		}
		//use printf with size 3 right justification
		//for (int i = 0; i < BINGO_KEYS.length(); i++) {
			//System.out.print(BINGO_KEYS.charAt(i) + " ");
			//System.out.println(this.card.get(BINGO_KEYS.charAt(i)));

		//}
	}
}