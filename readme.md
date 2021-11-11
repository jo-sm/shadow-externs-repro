# shadow-externs-repro

This is a basic repository reproducing an issue related to missing externs for `:target :npm-module` and exploring possible solutions.

# Running

```sh
> npm i
> npm run dev
```

This installs the local dependency [`random-module`](./random-module/index.js) and then runs the dev server, which watches the `src` directory and compiles the `app.main` namespace for both `:target :browser` and `:target :npm-module`.

# Discussion

As described in [`app.main`](./src/app/main.cljs) when using an anonymous function, the `:target :browser` build can successfully figure out the type of the binding for the result of calling a JavaScript function while `:target :npm-module` cannot. While this is expected (`:target :npm-module` doesn't so any analysis of JS code), a manual type hint on the binding doesn't help either, and the only way to fix the warning is to add the type hint to the anonymous function argument `%`. Since externs inferrencing is disabled this seems to point to an issue with the way `:target :npm-module` is compiled.

Is there a way to get around adding `^js` to the anonymous function argument? I tried to add a manual `externs.js` file but couldn't get it to work as well.
