package net.lelyak.reader;

import net.lelyak.model.BaseDTO;

import java.util.List;

/**
 * @author Nazar Lelyak.
 */
public interface Reader {

    String getFilePath();

    List<? extends BaseDTO> parseFile();
}
