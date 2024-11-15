import java.util.Scanner;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;


public class ImageTransform {

    public static float[] pixels;
    public static byte[] pixels2;
    public static Image lighten(Image srcImage) {
        // TODO: Task 1
        //throw new UnsupportedOperationException("Method not yet defined");
        pixels = srcImage.toFloatArray(Image.PixelFormat.RGB);
        for (int p = 0; p < pixels.length; p++) {
            pixels[p] *= 1.5;
        }
        return new Image(srcImage.getImageWidth(),srcImage.getImageHeight(), pixels, Image.PixelFormat.RGB);
    }


    public static Image greenShift(Image srcImage) {
        // TODO: Task 2
        //throw new UnsupportedOperationException("Method not yet defined");
        pixels = srcImage.toFloatArray(Image.PixelFormat.RGB);
        for (int p = 0; p < pixels.length; p+=3) { //p++ up until p=3 so that we only iterate through the three color channels
            pixels[p + 1] *= 2.5; //p+1 to get to the second index where green is
        }
        return new Image(srcImage.getImageWidth(),srcImage.getImageHeight(), pixels, Image.PixelFormat.RGB);
    }

    public static Image invert(Image srcImage) {
        pixels2 = srcImage.toByteArray(Image.PixelFormat.RGB);
        for (int p = 0; p < pixels2.length; p++) { 
            pixels2[p] = (byte) (255 - pixels2[p]);
        }
        return new Image(srcImage.getImageWidth(),srcImage.getImageHeight(), pixels2, Image.PixelFormat.RGB);
    }

    public static void main(String[] args) {
        Image srcImage = new Image("mscs-shield.png");
    
        Scanner scan = new Scanner(System.in);
        System.out.println("How would you like to transform your image?");
        System.out.println("1. Lighten");
        System.out.println("2. Green Shift");
        System.out.println("3. Invert");

        System.out.print("> ");
        int choice = scan.nextInt();

        Image transformed = switch(choice) {
            default -> srcImage; // If no matching choice, display original image
            case 1 -> lighten(srcImage);
            case 2 -> greenShift(srcImage);
            case 3 -> invert(srcImage);
        };

        CanvasWindow canvas = new CanvasWindow("img", 500, 500);
        canvas.add(transformed);
        transformed.setCenter(canvas.getCenter());

        scan.close();
    }
    
}
