package uk.ac.brunel.cs1702;

public class Matrix {

/**
* Constructor to create a matrix of size rows by cols and to initialise
* each element with the value of initialValue
*
* @param rows:
* an integer value indicating the number of rows
* @param cols:
* an integer value indicating the number of columns
* @param initialValue:
* each element of the matrix is initialised to this value
*/
	public int rows, cols;
	public int[][] initialData;
	public Matrix(int rows, int cols, int initialValue) {
		this.rows=rows;
		this.cols=cols;
		this.initialData = new int[rows][cols];

		for(int i=0;i<rows;i++)
		for(int j=0;j<cols;j++)
		initialData[i][j]=initialValue;
	}

/**
* Constructor to create a matrix of size equivalent to the size of int[][]
* initialData and to initialise each element with the values in
* initialData.
*
* @param initialData:
* An array of array of integers (int[][]) whose size determines
* the size of the matrix. The array is cloned into the Matrix
* object.
*/
	public Matrix(int[][] initialData) {
		this.initialData = initialData;
		this.rows=initialData.length;
		this.cols=initialData[0].length;
	}

/**
* @return Returns the number of rows of the Matrix object.
*/
	public int getRows() {
		return this.rows;
	}

/**
* @return Returns the number of columns of the Matrix object.
*/
	public int getCols() {
		return this.cols;
	}

/**
* @param i:
* data element row index starting from 0
* @param j:
* data element column index starting from 0
* @return: Returns the element at row i and column j of the Matrix object
*/
	public int getData(int i, int j) {
		if(i<this.getRows() && j<this.getCols() && i>=0 && j>=0)
		return this.initialData[i][j];
		return 0;
	}

/**
* @return Returns a 2-dimensional array of integers (int[][]) where the
* matrix elements are stored
*/
	public int[][] getData() {
		return this.initialData;
	}


	public boolean invalidInput(Matrix matrix) {
		if(matrix.getCols() != this.getCols() && matrix.getRows() != this.getRows())
		return true;
		return false;
	}

/**
* @param matrix:
* Matrix object to be added to the caller matrix
* @return Returns a new matrix object which is the sum of self and the
* parameter matrix. Returns null if dimensions do not match.
*/
	public Matrix add(Matrix matrix) {
		if(!invalidInput(matrix)){
		int i,j;
		int[][] result = new int[this.getRows()][this.getCols()];
		for(i=0;i<this.getRows();i++)
		for(j=0;j<getCols();j++)
		result[i][j]=this.getData(i,j)+matrix.getData(i,j);
		return new Matrix(result);
	}
		return null;

}

/**
* @param matrix:
* Matrix object to be subtracted from the caller matrix
* @return Returns a new Matrix object which is the difference of self and
* the parameter matrix. Returns null if dimensions do not match.
*/
	public Matrix sub(Matrix matrix) {
		if(!invalidInput(matrix)){
		int i,j;
		int[][] result = new int[this.getRows()][this.getCols()];
		for(i=0;i<this.getRows();i++)
		for(j=0;j<this.getCols();j++)
		result[i][j]=this.getData(i,j)-matrix.getData(i,j);
		return new Matrix(result);
	}
		return null;
	}

/**
* @return Returns the transpose of the matrix as a new Matrix object
*/
	public Matrix transpose() {
		Matrix result = new Matrix(this.getCols(),this.getRows(), 0);
		int i;
		for(i=0;i<result.getRows();i++)
		result.initialData[i]=this.getCol(i);
		return result;
	}

/**
* @param matrix:
* Matrix object to be concatenated to the caller matrix.
* @return: Returns the concatenation of the two matrices as a new Matrix
* object. Returns null if the number of columns does not match.
*/
	public Matrix concat(Matrix matrix) {
		if(this.getCols()==matrix.getCols()){
		int[][] result = new int[this.getRows()+matrix.getRows()][this.getCols()];
		int x;
		for(x=0;x<this.getRows();x++)
		result[x]=this.initialData[x];
		int i, j=0;
		for(i=x;i<result.length;i++)
		result[i]=matrix.initialData[j++];
		return new Matrix(result);
	}
		return null;

	}

/**
* @param row:
* row index starting from 1
* @return: Returns an array (int[]) containing the requested row. Returns null if the row does not exist.
*/
	public int[] getRow(int row) {
		if(row<this.getRows() && row>=0)
		return this.initialData[row];
		return null;

	}

/**
* @param col:
* column index starting from 1
* @return : Returns an array (int[]) containing the requested column. Returns null if the row does not exist.
*/
	public int[] getCol(int col) {
		if(col<this.getCols()&& col>=0){
		int[] colARR = new int[this.getRows()];
		int i;
		for(i=0;i<colARR.length;i++)
		colARR[i]=this.getData(i,col);
		return colARR;
	}
		return null;

	}

/**
* @return : Returns a String representation of the Matrix.
*/
	public String toString(){
		String result ="";
		String ls = System.getProperty("line.separator");
		for (int i = 0; i < this.getRows(); i++){
		for (int j = 0; j < this.getCols(); j++)
		result = result.concat(Integer.toString(this.getData(i,j)) + " ");
		result = result.concat(ls);
	}
		return result;
	}
	public static void main(String[] args){
		int[][] dataA = {
		{0,0 },
		{0, 0}
	};

		int[][] dataB = {
		{1, 1},
		{1, 1}
	};

		Matrix matrixA = new Matrix(dataA);
		Matrix matrixB = new Matrix(dataB);

System.out.println("A+B");
System.out.println(matrixA.add(matrixB).toString());
System.out.println("A-B");
System.out.println(matrixA.sub(matrixB).toString());
System.out.println("transpose(A)");
System.out.println(matrixA.transpose().toString());
System.out.println("A concat B");
System.out.println(matrixA.concat(matrixB).toString());
System.out.println("B concat A");
System.out.println(matrixB.concat(matrixA).toString());
System.out.println("(A concat B) + (B concat A)");
System.out.println(matrixA.concat(matrixB).add(matrixB.concat(matrixA)).toString());
System.out.println("A+B-B");
System.out.println(matrixA.add(matrixB).sub(matrixB).toString());
}
}
