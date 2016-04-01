package bean;

public class Toy {

    private int tid;
    private String toyName;
    private String toyDescription;
	private String toyCategory;
    private String toyImage;
    private float price;
    private boolean isRecycle;


    public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getToyName() {
		return toyName;
	}
	public void setToyName(String toyName) {
		this.toyName = toyName;
	}
	public String getToyDescription() {
		return toyDescription;
	}
	public void setToyDescription(String toyDescription) {
		this.toyDescription = toyDescription;
	}
	public String getToyCategory() {
		return toyCategory;
	}
	public void setToyCategory(String toyCategory) {
		this.toyCategory = toyCategory;
	}
	public String getToyImage() {
		return toyImage;
	}
	public void setToyImage(String toyImage) {
		this.toyImage = toyImage;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean isRecycle() {
		return isRecycle;
	}
	public void setRecycle(boolean isRecycle) {
		this.isRecycle = isRecycle;
	}