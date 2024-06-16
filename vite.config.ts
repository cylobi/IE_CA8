import reactSwc from '@vitejs/plugin-react-swc';
import type { UserConfigFn } from 'vite';
import { overrideVaadinConfig } from './vite.generated';

const customConfig: UserConfigFn = (env) => ({
  // build: {
  //   rollupOptions: {
  //     external: ['frontend/generated/vaadin.ts'], // Add your module here
  //   },
  // Here you can add custom Vite parameters
  // https://vitejs.dev/config/
  plugins: [
    reactSwc({
      tsDecorators: true,
    }),
  ],
});

export default overrideVaadinConfig(customConfig);
