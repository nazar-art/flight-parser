package net.lelyak.reader;

import net.lelyak.model.BaseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nazar Lelyak.
 */
@Component
public interface Reader {

    String getFilePath();

    List<? extends BaseDTO> parseFile();
}
