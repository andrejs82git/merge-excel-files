package zcrb.excel.merging;

import java.awt.dnd.*;
import java.io.File;
import java.util.List;
import java.awt.datatransfer.*;

abstract class MyDragDropListener implements DropTargetListener {

  @Override
  public void drop(DropTargetDropEvent event) {

    // Accept copy drops
    event.acceptDrop(DnDConstants.ACTION_COPY);

    // Get the transfer which can provide the dropped item data
    Transferable transferable = event.getTransferable();

    // Get the data formats of the dropped item
    DataFlavor[] flavors = transferable.getTransferDataFlavors();

    // Loop through the flavors
    for (DataFlavor flavor : flavors) {

      try {

        // If the drop items are files
        if (flavor.isFlavorJavaFileListType()) {

          // Get all of the dropped files
          List<File> files = (List<File>) transferable.getTransferData(flavor);

          if (files.size() == 1 && files.get(0).isDirectory()) {
            doDragFolder(files.get(0));
            System.out.println("Dragged files = " + files + "");
          }
        }

      } catch (Exception e) {

        // Print out the error stack
        e.printStackTrace();

      }
    }

    // Inform that the drop is complete
    event.dropComplete(true);

  }

  @Override
  public void dragEnter(DropTargetDragEvent event) {
  }

  @Override
  public void dragExit(DropTargetEvent event) {
  }

  @Override
  public void dragOver(DropTargetDragEvent event) {
  }

  @Override
  public void dropActionChanged(DropTargetDragEvent event) {
  }

  public abstract void doDragFolder(File f);
}