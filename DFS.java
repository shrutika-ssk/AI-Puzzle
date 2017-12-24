import java.io.*;
import java.util.*;
public class DFS {
	public int[][] solution;
	public int flag=0,  tempflag=0, noot=0;
	public static Scanner sc = new Scanner(System.in);
	public int backcol=-1, backrow=-1,nextrow=0, nextcol=0,count=1, placednext=0, backtrack=0, loopflag=0, getoutoffor=0, getnextpos=0;
	public int[] istree;
	public static boolean sometree=false;
	public int[] isliz;
	public static double maxL;
	public static long time1,time2;
	public Common c= new Common();
	
	public DFS(int N, int nool, int[][] sol) {
		time1 = System.currentTimeMillis();
		solution = new int[N][N];
		//solution = sol;
		
		istree= new int[N];
		isliz= new int[N];
			
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				solution[i][j] = sol[i][j];
				isliz[j]=0;
				if(solution[i][j]==2)
				{istree[j]++;//there is a tree in this column
				noot++;
				sometree=true;}
				}}
				
		}

	public void solve(int N, int nool) {
	int N1=N;
	maxL=(N1%2==0)?(Math.pow(N1/2,2)):(Math.pow((N1+1)/2,2));
	int bti=-1;
	try{
    PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
    
	int[][] back= new int[nool][2];
			if((sometree==false)&&(nool>N))
			{writer.print("FAIL\n");}
			
			else if((nool>maxL)||(nool>(N+noot)))
			{writer.print("FAIL\n");}
			
			else{
			placeLizards(0, N, nool, 0, 0, bti, back);
				if(flag==1){
				//print the result
				writer.print("OK\n");
				for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					writer.print(solution[i][j]);
				}
				writer.print("\n");
				}
					// time2 = System.currentTimeMillis();;
					// long timeTaken = (time2 - time1);  
					// writer.println("Time taken " + timeTaken + " ms");  
				}
			
		
				else{
				writer.print("FAIL\n");
				}
			}
		// time2 = System.currentTimeMillis();
		// long timeTaken = (time2 - time1);  
		// writer.println("Time taken " + timeTaken + " ms");
					
	writer.close();
	} catch (IOException e){};
	}
	
	public void placeLizards(int lizcounter, int N, int nool, int column, int row, int bti, int back[][]) {
	do{
	int[] temp= new int[2];
	int loopcount=0;
	for(int i= 0; i<N; i++)
	{
		for(int j=0;j<(N);j++)
		{	loopcount++;
			
			if(solution[j][i]==2)
				continue;
						
			if(backtrack==1)
			{	if(i<nextcol){
				continue;
				}
				if(i==nextcol){
				if(j<nextrow){
				continue;
			}}
			}
						
			backtrack=0;
			nextrow=(j+1)%N;
			nextcol=(nextrow==0)?(i+1)%N:i;
			if ((solution[j][i]==0)&&c.canPlace(solution, j, i))
			{//System.out.println("Placing "+j+i);
				solution[j][i]=1;
				lizcounter++;
				bti++;
				back[bti][0]=j;//row
				back[bti][1]=i;//column
				isliz[i]++;
				if(lizcounter==nool)
				{
				flag=1;
				break;
				}
				if(isliz[i]>istree[i])
				break;
			}
			if(lizcounter==nool)
			{
			flag=1;
			break;
			}
			if(isliz[i]>istree[i])
			{
					break;
			}
			// if((j==(N-1))&&(i==(N-1)))
			// {	if(isliz[i]==0)
				// {	if(istree[i]==0)
					// {
					// backtrack=1;
					// getoutoffor=1;
					// break;
					// }
				// }
			// }
		}
		if(flag==1)
		{break;}
		//if(temp[1]==N)
		//break;
		if(getoutoffor==1)
		{break;}
	
		if(isliz[i]==0)
			if((nool==N)&&(sometree==false))
				break;
		
	}
	if(lizcounter==0)
	if(loopflag==1)
		break;
	if(lizcounter==0)
	if(loopcount==N*N)
		loopflag=1;
	
	//map logic
	if(((bti>=0)&&(flag!=1))||((backtrack==1)&&(bti>=0)))
	{
	backcol=back[bti][1];
	backrow=back[bti][0];
	solution[backrow][backcol]=0;
	lizcounter--;	
	bti--;
	isliz[backcol]--;
	getoutoffor=0;
	
	nextrow=(backrow+1)%N;
	if (nextrow==0)
	{nextcol=(backcol+1);}
	else
	{nextcol=backcol;}
	backtrack=1;}
	
	} while((flag!=1)&&((System.currentTimeMillis()-time1)<275000));
	
	if(lizcounter==nool)
	{
	flag=1;
	return;
	}
	}
}