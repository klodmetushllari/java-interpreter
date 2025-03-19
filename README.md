# Java Interpreter

A simple command-line interpreter that supports basic arithmetic operations, variable assignments, and built-in functions.

## Features

- Interactive and file-based input modes
- Support for basic arithmetic operations (+, -, *, /)
- Variable assignments and references
- Built-in functions (sqrt, abs, min, max)
- String operations and comparisons
- Error handling with detailed messages

## Prerequisites

- Java JDK 11 or higher
- Git (for cloning the repository)

## Installation

1. Clone the repository:
```bash
git clone https://github.com/YOUR_USERNAME/interpreter.git
cd interpreter
```

2. Compile the project:
```bash
javac -d bin src/com/interpreter/*.java
```

## Usage

### Interactive Mode
```bash
java -cp bin com.interpreter.Main
```

### File Input Mode
```bash
java -cp bin com.interpreter.Main -f input.txt
```

## Examples

### Basic Arithmetic
```
> PRINT 2 + 3 * 4
14
```

### Variable Assignment
```
> ASSIGN x 10
10
> PRINT x
10
```

### Built-in Functions
```
> PRINT sqrt(16)
4.0
> PRINT min(5, 3)
3
```

### String Operations
```
> ASSIGN name "John"
John
> PRINT "Hello, " + name
Hello, John
```

### Error Handling
```
> PRINT undefined_var
RUNTIME ERROR at line 1, position 1:
  PRINT undefined_var
  ^
Undefined variable: undefined_var
```

## Input File Format

Create a text file (e.g., `input.txt`) with one command per line:
```
ASSIGN x 10
PRINT x
PRINT sqrt(x)
ASSIGN y 20
PRINT min(x, y)
```

## Error Types

- **Lexical Error**: Invalid characters or tokens
- **Syntax Error**: Invalid command or expression structure
- **Runtime Error**: Undefined variables, division by zero, etc.

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 