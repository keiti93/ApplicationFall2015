import java.util.Scanner;

public class WordCount{
	public static int count(char[][] map, String word) {
		if (map.length == 0) {
			return 0;
		}
		int rows = map.length;
		int columns = map[0].length;
		int result = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				for (int dx = -1; dx <= 1; dx++) {
					for (int dy = -1; dy <= 1; dy++) {
						if (dx == 0 && dy == 0) {
							continue;
						}
						int curRow = i;
						int curColumn = j;
						int index = 0;
						while (index < word.length()) {
							if (curRow < 0 || curRow >= rows || curColumn < 0 || curColumn >= columns) {
								break;
							}
							if (word.charAt(index) != map[curRow][curColumn]) {
								break;
							}
							index++;
							curRow += dx;
							curColumn += dy;
						}
						if (index == word.length()) {
							result++;
						}
					}
				}
			}
		}
		
		if (isPalindrome(word)) {
			result /= 2;
		}
		return result;
	}

	public static boolean isPalindrome(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word you want to count: ");
        String searched = scanner.next();
        System.out.println("Enter the number of rows and columns of the table: ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println("Enter the table with spaces between the characters: ");
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = scanner.next().charAt(0);
            }
        }
        
        System.out.println("The word " + searched + " is found " + count(map, searched) + " times in the table.");
    }
}
