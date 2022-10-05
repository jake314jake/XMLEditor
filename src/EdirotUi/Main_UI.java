package EdirotUi;

public class Main_UI {
	static Editor editor;
	public static void main(String[] args) {
		Config().Open();
	}
static Editor Config() {
	if(editor==null) {
		editor=new Editor();
	}
	return editor;
}
	
	
}
