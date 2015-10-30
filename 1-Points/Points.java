import java.util.Scanner;

public class Points {
	public int x;
	public int y;
	
	public Points(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return "(" + x + ", " + y +")";
	}
	
	public static String sort(String s){
		int counter = 0;
		String ss = "";
		for(int i = 0; i < s.length(); i++){
			if (s.charAt(i)=='~'){
				counter++;
				continue;
			}
			if (counter%2 == 0){
				ss+=s.charAt(i);
			} else {
				switch(s.charAt(i)){
				case '>': ss+='<'; break;
				case '<': ss+='>'; break;
				case '^': ss+='v'; break;
				case 'v': ss+='^'; break;
			}
		}
	}
		return ss;}
		
	public Points move(Points p, String s){
		for(int i = 0; i < s.length(); i++){
			switch(s.charAt(i)){
			case '>': p.x++; break;
			case '<': p.x--;; break;
			case '^': p.y--; break;
			case 'v': p.y++; break;
			}
		}
		return p;
	}

	public static void main (String args[]){
		Scanner scanner = new Scanner(System.in);
		int x;
		int y;
		String str = "";
		System.out.println("Starting point:");
		x = scanner.nextInt();
		y = scanner.nextInt();
		str = scanner.next();
		
		Points p = new Points(x, y);
		str = sort(str);
		System.out.println(p.move(p, str).toString());
	}
}
