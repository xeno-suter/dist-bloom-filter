# Bloom Filter
This Project is part of the course DIST.
The project is about implementing a Bloom Filter in Java.
With a small documentation in LaTeX.

---

## **Setup Instructions**

### **Requirements**
- **LaTex**
  - **Node.js** and **npm** installed on your system.
  - A LaTeX engine such as `pdflatex`, `xelatex`, or `lualatex` installed and available in your system's PATH.
- **Java**
  - **Java** installed on your system. (Version 22)

---

### **Installation** of LaTex with Pre-Commit
- Install the required npm dependencies: ```npm install```
- Initialize Husky for Git hooks: ```npx husky install```
- Add the pre-commit hook to run the LaTeX build: ```npx husky add .husky/pre-commit "npm run build-latex"```

### Usage
#### To compile LaTeX files manually:
```npm run build-latex```

#### When you commit changes, the pre-commit hook will:
- Automatically run the LaTeX build task.
- Abort the commit if there are any build errors.
