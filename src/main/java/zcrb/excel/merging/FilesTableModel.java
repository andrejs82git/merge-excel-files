package zcrb.excel.merging;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import zcrb.excel.merging.utils.UtfResourceBundle;

public class FilesTableModel implements TableModel {
  private UtfResourceBundle bundle = new UtfResourceBundle(ResourceBundle.getBundle("bundle"));

  private List<String> headerList = Arrays.asList(new String[]{
    bundle.getString("FilesTableModel.headerList.id"),
    bundle.getString("FilesTableModel.headerList.fileName"),
    bundle.getString("FilesTableModel.headerList.size"),
    bundle.getString("FilesTableModel.headerList.changeDate"),
  });

  private List<Class<?>> dataClass = Arrays
      .asList(new Class[] { Integer.class, String.class, String.class, Date.class });

  private List<File> data = new ArrayList<>();

  public void setNewData(List<File> newData) {
    TableModelEvent e = new TableModelEvent(this);
    this.data = newData;
    tableModelListeners.forEach(item -> item.tableChanged(e));
  }

  @Override
  public int getRowCount() {
    return data.size();
  }

  @Override
  public int getColumnCount() {
    return headerList.size();
  }

  @Override
  public String getColumnName(int columnIndex) {
    return headerList.get(columnIndex);
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return dataClass.get(columnIndex);
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    File file = data.get(rowIndex);
    switch (columnIndex) {
    case (0):
      return rowIndex + 1;
    case (1):
      return file.getName();
    case (2):
      return (file.length() / 1000) + "KB";
    case (3):
      return (new Date(file.lastModified()));
    }
    return null;
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

  }

  private List<TableModelListener> tableModelListeners = new ArrayList<>();

  @Override
  public void addTableModelListener(TableModelListener tableModelListener) {
    tableModelListeners.add(tableModelListener);

  }

  @Override
  public void removeTableModelListener(TableModelListener tableModelListener) {
    tableModelListeners.remove(tableModelListener);
  }

}
