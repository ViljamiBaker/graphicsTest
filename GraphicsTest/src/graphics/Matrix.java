package graphics;
public class Matrix {
    private int rows;
    private int columns;
    private double[][] data;

    public Matrix(int columns, int rows){
        this.rows = rows;
        this.columns = columns;
        this.data = new double[rows][columns];
        for(int c = 0; c<columns; c++){
            for(int r = 0; r<rows; r++){
                data[r][c] = 0.0;
            }
        }
    }

    public Matrix add(Matrix other){
        if(other.getColumns() != columns || other.getRows() != rows){
            throw new IllegalArgumentException("Rows or columns not equal: " + other.getColumns() + "!=" + columns + " || " + other.getRows() + "!=" + rows);
        }
        Matrix newMatrix = new Matrix(columns, rows);
        for(int r = 0; r<rows; r++){
            for(int c = 0; c<columns; c++){
                newMatrix.setData(r, c, getData(r,c)+other.getData(r,c));
            }
        }
        return newMatrix;
    }

    public Matrix multiply(Matrix other){
        if(columns != other.getRows()){
            throw new IllegalArgumentException("Other columns not equal this rows: " + columns + "!=" + other.getRows());
        }
        Matrix newMatrix = new Matrix(other.getColumns(), rows);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < other.getColumns(); c++) {
                double newData = 0;
                for (int i = 0; i < other.getRows(); i++){
                    newData += getData(r, i) * other.getData(i, c);
                }
                newMatrix.setData(r, c, newData);
            }
        }
        return newMatrix;
    }

    public Matrix scalarMult(double other){
        Matrix newMatrix = new Matrix(columns, rows);
        for(int c = 0; c<columns; c++){
            for(int r = 0; r<rows; r++){
                newMatrix.setData(r, c, other * getData(r,c));
            }
        }
        return newMatrix;
    }

    public Matrix transpose(){
        Matrix newMatrix = new Matrix(rows, columns);
        for(int c = 0; c<columns; c++){
            for(int r = 0; r<rows; r++){
                newMatrix.setData(c, r, getData(r, c));
            }
        }
        return newMatrix;
    }

    public void setData(int r, int c, Double data){
        checkBounds(r, c);
        this.data[r][c] = data;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double getData(int r, int c) {
        checkBounds(r,c);
        return data[r][c];
    }

    public double[][] getData() {
        return data;
    }

    private void checkBounds(int r, int c){
        boolean bad = false;
        if(r>=rows||r<0){
            System.out.println("Row " + r + " out of bounds for size " + rows);
            bad = true;
        }
        if(c>=columns||c<0){
            System.out.println("Column " + c + " out of bounds for size " + columns);
            bad = true;
        }
        if(bad){
            throw new IllegalArgumentException("Out of bounds!");
        }
    }

    @Override
    public String toString(){
        String string = "[\n";
        for(int r = 0; r<rows; r++){
            string += "    [";
            for(int c = 0; c<columns-1; c++){
                string += getData(r,c);
                string += ", ";
            }
            string += getData(r,columns-1);
            string += "]\n";
        }
        string += "]";
        return string;
    }

    public static void main(String[] args) {
        Matrix mat1 = new Matrix(3, 3);
        mat1.setData(0, 0, 10.0);
        mat1.setData(1, 0, 10.0);
        mat1.setData(2, 0, 100.0);
        mat1.setData(2, 1, 2.0);
        Matrix mat2 = new Matrix(3, 4);
        mat2.setData(0, 0, 20.0);
        mat2.setData(2, 1, 2.0);
        System.out.println(mat1);
        System.out.println(mat2.transpose());
        System.out.println(mat1.multiply(mat2.transpose()));
    }
}
