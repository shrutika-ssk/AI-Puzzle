public class Common {
	
public boolean canPlace(int[][] matrix, int row, int column) {
		// since we are filling one column at a time,
		// we will check if no queen is placed in that particular row
		for (int i = column-1; i >=0; i--) {
			if (matrix[row][i] == 1) {
				//System.out.println("1");
				return false;
			}
			if (matrix[row][i]== 2)
			{break;}
		}
			for (int i = column+1; i < matrix.length; i++) {
			if (matrix[row][i] == 1) {
			//	System.out.println("2");
				return false;
			}
			if (matrix[row][i] == 2) 
			{ break;}
		}
		for (int i = row-1; i >=0; i--) {
			if (matrix[i][column] == 1) {
		//		System.out.println("3");
				return false;
			}
			if (matrix[i][column] == 2)
			{break;}
		}
		for (int i = row+1; i < matrix.length; i++) {
			if (matrix[i][column] == 1) {
	//			System.out.println("4");
				return false;
			}
			if (matrix[i][column] == 2)
			{break;}
		}
		// we are filling one column at a time,so we need to check the upper and
		// diagonal as well
		// check upper left diagonal
		for (int i = row-1, j = column-1; i >= 0 && j >= 0; i--, j--) {
			if (matrix[i][j] == 1) {
				//System.out.println("5");
				return false;
			}
			if (matrix[i][j] == 2)
			{break;}
		}
		
		//upper right diagonal
		for (int i = row-1, j = column+1; i >=0 && j < matrix.length; i--, j++) {
			if (matrix[i][j] == 1) {
			//	System.out.println("6");
				return false;
			}
			if (matrix[i][j] == 2)
			{break;}
		}

		// check lower left diagonal
		for (int i = row+1, j = column-1; i < matrix.length && j >= 0; i++, j--) {
			if (matrix[i][j] == 1) {
				//System.out.println("7");
				return false;
			}
			if (matrix[i][j] == 2)
			{break;}
		}
		// check lower right diagonal
		for (int i = row+1, j = column+1; i < matrix.length && j < matrix.length; i++, j++) {
			if (matrix[i][j] == 1) {
			//	System.out.println("8");
				return false;
			}
			if (matrix[i][j] == 2)
			{break;}
		}
		// if we are here that means we are safe to place Queen at row,column
		//System.out.println("9");
		return true;
	}	
	
	public boolean isgoalstate(int[][] matrix, int N, int nool)
	{int count=0;
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
			if(matrix[i][j]==1)
				count++;
			}
		}
		if (count==nool)
			return true;
		
		return false;
	}
	
public void matrixprint(int N, int[][] matrix)
{
	for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(matrix[i][j]);
				}
				System.out.println();
				}  
}


public int[] nextsafebfs(int r, int c, int N, int[][] matrix)
{int[] temp= new int[2];
	for(int i=r; i<N; i++)
		for(int j=c; j<N; j++)
		{
			if(matrix[i][j]==2)
			{
				temp[0]=i;
				temp[1]=j;
				return temp;
			}
			if((i!=(N-1))&&(j==(N-1)))
			{
				temp[0]=(r+1);
				temp[1]=0;
				return temp;
			}
			if((i==N-1)&&(j==(N-1)))
			{
				temp[0]=N-1;
				temp[1]=N-1;
				return temp;
			}
		}
		temp[0]=N-1;
		temp[1]=N-1;
				
	return temp;
}
}