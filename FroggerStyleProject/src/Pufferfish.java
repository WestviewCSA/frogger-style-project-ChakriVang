import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Pufferfish {
private Image forward;
private AffineTransform tx;

int dir = 0; // 0-forward, 1-backward, 2-left, 3-right
int width, height;
int x, y; // Position of the object
int vx, vy; // Movement variables
double scaleWidth = 1; // Scale image width
double scaleHeight = 1; // Scale image height
int spacing = 100;

public Pufferfish() {
forward = getImage("/imgs/Puffer.png");//Load the image for the

// Initialize size, position, and velocity
width = (int) (26*scaleWidth); // Set width from image dimensions
height =(int) (26*scaleHeight); // Set height from image dimensions

x = width;
y = height;

vx = -2; // Set velocity to 2 pixels per frame (speed of movement)
vy = 0; // No vertical movement

tx = AffineTransform.getTranslateInstance(0, 0);
init(x, y); // Initialize the location of the image


}
public boolean collided(Marlin character) {
	Rectangle main = new Rectangle(
			character.getX(),
			character.getY(),
			character.getWidth(),
			character.getHeight()
			);
	Rectangle Pufferfish = new Rectangle(x,y,width,height);
	return main.intersects(Pufferfish);
}

public Pufferfish(int x, int y, int spacing, int vx) {
	this();
	this.x=x;
	this.y=y;
	this.spacing=spacing;
	this.vx=vx;
	
		
}

public Rectangle getHitbox() {
	return new Rectangle(x,y,width, height);
}

public void paint(Graphics g) {
// Cast graphics to Graphics2D for transformation
Graphics2D g2 = (Graphics2D) g;

x += vx;
y += vy;
if(vx>0) {
if (x >=Frame.width + spacing - (Frame.width%spacing)) {
	
x = -spacing; // Reset position to the left side of the screen (move it back

}
}else if(vx<0) {
	if (x <= -spacing) {
		
		x = Frame.width + spacing - (Frame.width%spacing); // Reset position to the left side of the screen (move it back

		}
}

// Update the translation matrix for rendering
init(x, y);

// Draw the Pufferfish image based on its direction

//if (x>700) {
//	x=700/2-width/2;
//	y=770;
//}else if(x<-60) {
//	x=700/2-width/2;
//	y=770;
//}else if(y<40) {
//	x=700/2-width/2;
	//y=770;
//	System.out.println("You Won!");
//}

g2.drawImage(forward, tx, null);
if (Frame.debugging) {
g.setColor(Color.green);
//g.drawRect(x, y, width, height); // Optional: Draw a bounding
}
}


private void init(double a, double b) {
// Initialize the position and scaling
tx.setToTranslation(a, b);
tx.scale(scaleWidth, scaleHeight);
}

private Image getImage(String path) {
Image tempImage = null;
try {
URL imageURL = Pufferfish.class.getResource(path);
if(imageURL!=null) {
tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
}else {
	System.err.println("image not found"+ path);
}
} catch (Exception e) {
e.printStackTrace();
}
return tempImage;
}

}