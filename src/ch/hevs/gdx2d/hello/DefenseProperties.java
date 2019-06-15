package ch.hevs.gdx2d.hello;
/**
 * Structure to define a defense
 */
public class DefenseProperties {
	
	public String pickImage;
	public String previewImage;
	public float radius;
	public String classDefense;
	public int price;
	
	DefenseProperties(String pickImage,String previewImage,	float radius,String classDefense,int price){
		this.pickImage = pickImage;
		this.previewImage = previewImage;
		this.radius = radius;
		this.classDefense = classDefense;
		this.price = price;
	}
}
