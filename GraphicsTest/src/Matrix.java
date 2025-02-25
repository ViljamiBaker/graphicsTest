import javax.sql.RowSet;

public class Matrix <T extends Number>{
    private int rows;
    private int columns;
    private T[][] data;
    @SuppressWarnings ("unchecked")
    public Matrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.data = (T[][]) new Number[rows][columns];
        for(int c = 0; c<columns; c++){
            for(int r = 0; r<rows; r++){
                data[r][c] = (T)Double.valueOf(0.0);
            }
        }
    }
    @SuppressWarnings ("unchecked")
    public Matrix<T> add(Matrix<T> other){
        if(other.getColumns() != columns || other.getRows() != rows){
            throw new IllegalArgumentException("Rows or columns not equal: " + other.getColumns() + "!=" + columns + " || " + other.getRows() + "!=" + rows);
        }
        Matrix<T> newMatrix = new Matrix<>(columns, rows);
        for(int r = 0; r<rows; r++){
            for(int c = 0; c<columns; c++){
                newMatrix.setData(r, c, (T)Double.valueOf(other.getData(r, c).doubleValue() + getData(r, c).doubleValue()));
            }
        }
        return newMatrix;
    }

    @SuppressWarnings ("unchecked")
    public T dotProd(Matrix<T> other, int row, int column){
        if(other.getRows() != columns){
            throw new IllegalArgumentException("Other columns not equal this rows: " + other.getRows() + "!=" + columns);
        }
        double totalValue = 0;
        for(int i = 0; i<columns; i++){
            totalValue += getData(i, column).doubleValue() + other.getData(row,i).doubleValue();
        }
        return (T)Double.valueOf(totalValue);
    }

    public Matrix<T> multiply(Matrix<T> other){
        if(other.getRows() != columns){
            throw new IllegalArgumentException("Other columns not equal this rows: " + other.getRows() + "!=" + columns);
        }
        Matrix<T> newMatrix = new Matrix<>(columns, other.getRows());
        for(int r = 0; r<other.getRows(); r++){
            for(int c = 0; c<columns; c++){
                newMatrix.setData(r, c, dotProd(other, r, c));
            }
        }
        return newMatrix;
    }

    @SuppressWarnings ("unchecked")
    public Matrix<T> scalarMult(double other){
        Matrix<T> newMatrix = new Matrix<>(columns, rows);
        for(int c = 0; c<columns; c++){
            for(int r = 0; r<rows; r++){
                newMatrix.setData(r, c, (T)Double.valueOf(other * getData(r,c).doubleValue()));
            }
        }
        return newMatrix;
    }

    public Matrix<T> transpose(){
        Matrix<T> newMatrix = new Matrix<>(rows, columns);
        for(int c = 0; c<columns; c++){
            for(int r = 0; r<rows; r++){
                newMatrix.setData(c, r, getData(r, c));
            }
        }
        return newMatrix;
    }

    public void setData(int r, int c, T data){
        this.data[r][c] = data;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public T getData(int r, int c) {
        return data[r][c];
    }

    public T[][] getData() {
        return data;
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
        Matrix<Double> mat1 = new Matrix<>(2, 3);
        mat1.setData(0, 0, 10.0);
        mat1.setData(1, 0, 10.0);
        Matrix<Double> mat2 = new Matrix<>(2, 3);
        mat2.setData(0, 0, 20.0);
        System.out.println(mat1.add(mat2));
        System.out.println(mat1.scalarMult(2));
        System.out.println(mat1);
        System.out.println(mat2.transpose());
        System.out.println(mat1.multiply(mat2.transpose()));
    }
}
