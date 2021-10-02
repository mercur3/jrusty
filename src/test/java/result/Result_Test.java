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

    @Test
    void err_path_works() {
        var str = "Some error type";
        var x = new Err<String, Integer>(str);

        assertFalse(x.isOk());
        assertTrue(x.isErr());
        assertEquals(x.ok(), Optional.empty());
        assertEquals(x.err(), Optional.of(str));
        assertThrows(IllegalStateException.class, x::unwrap);
        assertEquals(x.unwrapOr(1), 1);
    }

    @Test
    void function_works() {
        int num = 1234;
        var ok = parseInt("1234");
        var err = parseInt("LOL");

        assertTrue(ok.isOk());
        assertFalse(ok.isErr());
        assertEquals(ok.ok(), Optional.of(num));
        assertEquals(ok.err(), Optional.empty());
        assertEquals(ok.unwrap(), num);
        assertEquals(ok.unwrapOr(0), num);

        assertFalse(err.isOk());
        assertTrue(err.isErr());
        assertEquals(err.ok(), Optional.empty());
        assertEquals(err.err(), Optional.of("It cannot be parsed"));
        assertThrows(IllegalStateException.class, err::unwrap);
        assertEquals(err.unwrapOr(1), 1);
    }

    @Test
    void null_not_allowed() {
        assertThrows(NullPointerException.class, () -> new Ok<>(null));
        assertThrows(NullPointerException.class, () -> new Err<>(null));
    }



    /*     PRIVATE     */



    private static Result<Integer, String> parseInt(String str) {
        try {
            return new Ok<>(Integer.parseInt(str));
        }
        catch (NumberFormatException e) {
            return new Err<>("It cannot be parsed");
        }
    }
}
