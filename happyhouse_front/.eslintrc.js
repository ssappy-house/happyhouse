module.exports = {
  root: true,
  env: {
    node: true,
  },
  extends: [
    "plugin:vue/essential",
    "eslint:recommended",
    "plugin:prettier/recommended",
  ],
  rules: {
    "no-console": process.env.NODE_ENV === "production" ? "error" : "off",
    "no-debugger": process.env.NODE_ENV === "production" ? "error" : "off",
    "prettier/prettier": [
      "warn",
      {
        singleQuote: false,
        semi: true,
        tabWidth: 2,
        trailingComma: "all",
        printWidth: 80,
        bracketSpacing: true,
        arrowParens: "always",
        endOfLine: "auto",
        useTabs: false,
      },
    ],

    // eslint 관련 오류
    "vue/multi-word-component-names": "off",
    "vue/no-unused-vars": "off",
    "vue/no-unused-components": "off",
  },
  parserOptions: {
    parser: "@babel/eslint-parser",
  },
};
