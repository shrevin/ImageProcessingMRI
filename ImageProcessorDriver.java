import java.util.Scanner;

public class ImageProcessorDriver {
	private static Scanner scan = new Scanner(System.in);
	private static String file;
	
	public static void main (String[]args) {
		
		boolean keepGoing = true;
		while (keepGoing) {
			System.out.print("Please enter a filename (to quit enter q): ");
			file = scan.next();
			if (file.equals("q")) {
				break;
			} else {
				ImageProcessor mri = new ImageProcessor(file);
				ImageProcessor mriOriginal = new ImageProcessor(file);
				
				//last three represent rgb values for the tumor
				mri.outputTumorRegion(0,0,0,255,255,255);
				mriOriginal.drawOriginalImage();
				System.out.println(mri.identifyMeningiomaLeftSide());
				mri.drawChangedImage();
			}
		}
		
	}
	
	
}
	

