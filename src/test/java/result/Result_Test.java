package result;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class Result_Test {
    @Test
    void ok_path_works() {
        var str = "Hello, world!";
        var x = new Ok<>(str);

        assertTrue(x.isOk());
        assertFalse(x.isErr());
        assertEquals(x.ok(), Optional.of(str));
        assertEquals(x.err(), Optional.empty());
        assertEquals(x.unwrap(), str);
        assertEquals(x.unwrapOr(""), str);
    }
}
