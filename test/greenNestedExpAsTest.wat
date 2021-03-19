(module (func (export "main") (result i32)
    i32.const 1 i32.const 1 i32.sub if (result i32) i32.const 1 else i32.const 44 end
    return)
    )