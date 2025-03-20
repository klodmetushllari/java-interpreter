# Java Interpreter

A command-line interpreter implemented in Java that follows a classic interpreter architecture with lexical analysis, parsing, and execution phases. The interpreter supports basic arithmetic operations, variable management, and built-in functions.

## Architecture

The project implements a classic interpreter design pattern with the following components:

- **Lexer**: Converts input text into tokens
- **Parser**: Transforms tokens into an Abstract Syntax Tree (AST)
- **Evaluator**: Executes the AST nodes to produce results

### Design Patterns

The implementation utilizes several design patterns:
- **Interpreter Pattern**: For parsing and evaluating expressions
- **Composite Pattern**: In the AST structure
- **Strategy Pattern**: For different input handling strategies
- **Visitor Pattern**: For evaluating AST nodes

## Features

- Interactive and file-based input modes
- Support for basic arithmetic operations (+, -, *, /)
- Variable assignments and references
- Built-in functions (sqrt, abs, min, max)
- String operations and comparisons
- Comprehensive error handling with detailed messages
- Built-in help system

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
# or
java -jar interpreter.jar
```

### File Input Mode
```bash
java -cp bin com.interpreter.Main -f input.txt
# or
java -jar interpreter.jar -f input.txt
```

### Help Options
```bash
java -cp bin com.interpreter.Main -h
# or
java -jar interpreter.jar --help
```

## Error Handling

The interpreter provides detailed error messages for:
- **Lexical Error**: Invalid characters or tokens
- **Syntax Error**: Invalid command or expression structure
- **Runtime Error**: Undefined variables, division by zero, etc.

Each error includes context information showing the exact line and position where the error occurred.

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

## Input File Format

Create a text file (e.g., `input.txt`) with one command per line:
```
ASSIGN x 10
PRINT x
PRINT sqrt(x)
ASSIGN y 20
PRINT min(x, y)
```

## Available Commands

- `HELP`: Display the command guide and usage information
- `EXIT`: Exit the interpreter
- `PRINT expr`: Print the value of an expression
- `ASSIGN var expr`: Assign a value to a variable

## Expression Types

- **Numbers**: 42, 3.14, -10
- **Strings**: "Hello", 'World'
- **Variables**: x, myVar, counter
- **Operators**: +, -, *, /, ==, !=, <, >, <=, >=
- **Functions**: sqrt(x), abs(x), min(x,y), max(x,y)

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 