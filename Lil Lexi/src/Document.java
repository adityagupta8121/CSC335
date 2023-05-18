import java.util.ArrayList;
import java.util.List;

public abstract class Document {

	private List<Row> rows;
	
	public Document(){
		this.rows = new ArrayList<Row>();
	}
	
	public Document(List<Row> rows){
		this.rows = rows;
	}
	
	public List<Row> getRows(){
		return this.rows;
	}
	
	public void setRows(List<Row> rows){
		this.rows = rows;
	}
	
	public void draw(List<Row> rows){		
		this.setRows(rows);
		// System.out.println("at main draw: " + rows.size() + " " + this.getRows().size());
			}
}
