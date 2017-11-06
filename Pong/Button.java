public class Button {
	
	private Rectangle rect;
	private String name = "";
	
	public Button(Rectangle rect) {
		this.rect = rect;
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}	
		
}
