(module (func (export "main") (result i32)
    i32.const 1 if (result i32) i32.const 1 else i32.const 0 end if (result i32) i32.const 2 else i32.const 3 end
    return)
    )