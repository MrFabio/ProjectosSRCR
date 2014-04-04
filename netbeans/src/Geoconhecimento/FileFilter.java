
package Geoconhecimento;

import java.io.File;

class FileFilter extends javax.swing.filechooser.FileFilter {
    @Override
    public boolean accept(File f) {
        // Allow only directories, or files with ".txt" extension
        return
                f.isDirectory() ||
                f.getAbsolutePath().endsWith(".l") ||
                f.getAbsolutePath().endsWith(".pl");
    }
    @Override
    public String getDescription() {
        return "Prolog database (*.l, *.pl)";
    }
} 
