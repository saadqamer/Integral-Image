package A1Q2;

/**
 * Represents an integer integral image, which allows the user to query the mean
 * value of an arbitrary rectangular subimage in O(1) time.  Uses O(n) memory,
 * where n is the number of pixels in the image.
 *
 * @author jameselder
 */
public class IntegralImage {

    private final int[][] integralImage;
    private final int imageHeight; // height of image (first index)
    private final int imageWidth; // width of image (second index)

    /**
     * Constructs an integral image from the given input image.  
     *
     * @author jameselder
     * @param image The image represented
     * @throws InvalidImageException Thrown if input array is not rectangular
     */
    public IntegralImage(int[][] image) throws InvalidImageException {
    	
    	imageWidth = image[0].length; //number of columns
    	imageHeight = image.length;		//number of rows
    	boolean isJagged = false;		//variable to test for rectangular of subImage
    	Integer prevCols = null;	//variable to help in test for rectangular subImage
       integralImage = new int[imageHeight][imageWidth]; //construct 2d array with rows and cols from given image
       
       if(image!=null)
       {
    	   for(int i = 0; i < image.length; i++)	//loop through rows
    	   {
    		   if(prevCols == null)
    		   {
    			   prevCols = image[i].length;		//if in first row, previous number of columns is the current number of columns in the row
    		   }
    		   else if(prevCols == image[i].length)	//if the current row has the same number of columns as the previous row
    		   {
    			   continue;
    		   }
    		   else
    		   {
    			   isJagged = true;						
    			  throw new InvalidImageException();	//if the current row does not have the same number of columns as the previous row array is jagged and throw exception
    		   }
    	   }
       }
       
       //loop with nested loop to construct array
       for(int row=0; row < image.length;row++)
       {
    	   for(int col = 0; col < image[0].length; col++)
    	   {
    		   integralImage[row][col] = image[row][col];
    		   
    	   }
       }
       
    }

    /**
     * Returns the mean value of the rectangular sub-image specified by the
     * top, bottom, left and right parameters. The sub-image should include
     * pixels in rows top and bottom and columns left and right.  For example,
     * top = 1, bottom = 2, left = 1, right = 2 specifies a 2 x 2 sub-image starting
     * at (top, left) coordinate (1, 1).  
     *
     * @author jameselder
     * @param top top row of sub-image
     * @param bottom bottom row of sub-image
     * @param left left column of sub-image
     * @param right right column of sub-image
     * @return 
     * @throws BoundaryViolationException if image indices are out of range
     * @throws NullSubImageException if top > bottom or left > right
     */
   public double meanSubImage(int top, int bottom, int left, int right) throws BoundaryViolationException, NullSubImageException {
        
	   int row,col;
	   int count = 0;
	   double total = 0.0;
	   
	   if(top < 0 || bottom < 0 || left < 0 || right < 0)	//if any parameter entered is negative throw exception
	   {
		   throw new BoundaryViolationException();
	   }
	   else if((top > 0 && top > integralImage.length)||(bottom > 0 && bottom > integralImage.length) || (left > 0 && left > integralImage[0].length)
			   ||(right > 0 && right > integralImage[0].length)) //if any parameter is greater than 0 and outside array boundaries, then throw exception
	   {
		   throw new BoundaryViolationException();
	   }
	   else if(top > bottom || left > right)	//if top or left is greater than the bottom parameter then throw exception
	   {
		   throw new NullSubImageException();
	   }
	   else
	   {
		   //loop and nested loop to construct subimage
		   for(row = top;row <= bottom; row++)
		   {
			   for(col = left;col<=right;col++)
			   {
				   total+=integralImage[row][col]; //calculate total of subimage
				   count++;							//increase the counter for each element in the subimage to determine mean
			   }
		   }
		   
		   total = total / count; //calculate mean
		   
	        return total; 
	   }
	   
	  
    }
}