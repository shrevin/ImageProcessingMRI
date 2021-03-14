import images.APImage;
import images.Pixel;

public class ImageProcessor{
	private APImage originalImage;	
	private APImage copyImage;	
	private boolean isMeningioma = false;
	
	
	public ImageProcessor(String fileName) {
		originalImage = new APImage(fileName);
		copyImage = originalImage.clone();
	}
	
	public int randomNum() {
		return (int)(Math.random()*256);
	}
	
	public boolean getIsMeningioma() {
		return isMeningioma;
	}
	
	public void drawOriginalImage() {
		copyImage.draw();
	}
	
	public void drawChangedImage() {
		copyImage.draw();
	}
	
	public void median() {
		// removing noise with a median filter
		for (int row = 1; row < originalImage.getWidth()-1; row++) {
			for (int col = 1; col < originalImage.getHeight()-1; col++) {
				Pixel p = copyImage.getPixel(row, col);
				Pixel pL = originalImage.getPixel(row, col-1);
				Pixel pR = originalImage.getPixel(row, col+1);
				int red = (pL.getRed() + pR.getRed())/2;
				int green = (pL.getRed() + pR.getRed())/2;
				int blue = (pL.getRed() + pR.getRed())/2;
				p.setRed(red);
				p.setGreen(green);
				p.setBlue(blue);
			}
					
		}		
	}
	
	public void outputTumorRegion(int r1, int g1, int b1, int r2, int g2, int b2 ) {
		
		// grayscaling the image
		for (Pixel p: copyImage) {
				int red = p.getRed();
				int green = p.getGreen();
				int blue = p.getBlue();
				int average = (red + green + blue)/3;
				p.setRed(average);
				p.setGreen(average);
				p.setBlue(average);
		}
		
		// removing noise with a median filter
		median();

		// black and white
		for (Pixel p : copyImage){
			int red = p.getRed();
			int green = p.getGreen();
			int blue = p.getBlue();
			int average = (red + green + blue) / 3;
			
			if (average < 128){
				p.setRed(r1);
				p.setGreen(g1);
				p.setBlue(b1);
			} else {
				p.setRed(r2);
				p.setGreen(g2);
				p.setBlue(b2);
			}
		}
		
	}
	
	public boolean identifyMeningiomaLeftSide() {
		int measure = copyImage.getWidth()/6;
		for (int row = 0; row < copyImage.getWidth()/2; row++) {
			for (int col = 0; col < copyImage.getHeight()/2; col++) {
				Pixel p = copyImage.getPixel(row, col);
				if (p.getRed() == 255 && p.getGreen() == 255 && p.getBlue()== 255) {
					Pixel pNew = copyImage.getPixel(row, col+measure);
					Pixel pNew2 = copyImage.getPixel(row, col+measure+10);
					if (pNew.getRed() == 255 && pNew2.getRed() == 255) {
						return true;
					} 
				}
			}
		}
		return false;
	}
	
	
	
	
		
}
