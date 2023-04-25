# how to symlinks in windows
1. open cmd as admin
2. got to the target folder for the symlink (the link should be relative for git
to work nicely) 
3. run the `mklink command`

````
mklink the_link.md  ..\the_original.md
````
